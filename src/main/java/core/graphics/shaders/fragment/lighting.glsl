vec3 getNormal(vec3 p)
{
    vec2 e = vec2(EPSILON, 0.0);
    vec3 n = vec3(map(p).x) - vec3(map(p - e.xyy).x, map(p - e.yxy).x, map(p - e.yyx).x);
    return normalize(n);
}

float getSoftShadow(vec3 p, vec3 lightPos)
{
    float res = 1.0;
    float dist = EPSILON;
    float lightSize = 0.03;

    for(int i = 0; i < uMaxSteps; i++)
    {
        vec2 hit = map(p + lightPos * dist);

        if(uIDs[int(hit.y) - 1] == 0)
        {
            res = min(res, hit.x / (dist * lightSize));
            dist += hit.x;
        }

        if(hit.x < EPSILON || dist > 60.0)
        {
            break;
        }
    }
    return clamp(res, 0.0, 1.0);
}

float getAmbientOcclusion(vec3 p, vec3 normal)
{
    float occ = 0.0;
    float weight = 1.0;
    for(int i = 0; i < 8; i++)
    {
        float len = 0.01 + 0.02 * float(i * i);
        float dist = map(p + normal * len).x;
        occ += (len - dist) * weight;
        weight *= 0.85;
    }

    return 1.0 - clamp(0.6 * occ, 0.0, 1.0);
}

vec3 getLight(vec3 p, vec3 rd, float id, vec3 lightPos[BODY_NUM_LIMIT], vec3 bgColor)
{
    vec3 finalCol = vec3(0, 0, 0);
    vec3 V = -rd;
    vec3 N = getNormal(p);
    vec3 col = getPlanet(p, id, N, bgColor);

    if(id == -1)
    {
        return vec3(0);
    }

    if(id == -2)
    {
        return col;
    }

    for(int i = 0; i < uLightSourcesNum; i++)
    {
        vec3 L = normalize(lightPos[i] - p);
        vec3 R = reflect(-L, N);
        vec3 H = normalize(L + V);

        if(uIDs[int(id) - 1] == 1 || uEnableLighting == 0)
        {
            return col;
        }

        vec3 ambient = col * 0.1;
        vec3 diffuse = col * clamp(dot(L, N), 0.0, 1.0);
        vec3 specColor = vec3(0.1);
        vec3 specular = specColor * pow(max(dot(N, H), 0.0), 5.0); // clamp(dot(R, V), 0.0, 1.0)
        vec3 fresnel = 0.25 * col * pow(1.0 + dot(rd, N), 3.0);

        // shadows
        float shadow = getSoftShadow(p + N * 0.02, L);

        // ambient occlusion
        float occ = getAmbientOcclusion(p, N);

        // reflections
        vec3 back = 0.05 * col * clamp(dot(N, -L), 0.0, 1.0);

        //finalCol = mix(finalCol, (ambient + fresnel + back) * occ + (diffuse + specular * occ), 1); // * shadow
        finalCol += (ambient + fresnel + back) * occ + (diffuse + specular * occ) * shadow; // * shadow
    }
    return finalCol;
}

vec3 getGlow(vec3 glowColor, float nearest, float value)
{
    float glowValue = abs(0.3 * 4.83 / value); // smaller - bigger
    float glowSize = 0.55; // smaller - bigger
    float glow = pow(nearest + glowValue, -glowSize);
    return glow * glowColor;
}

vec3 gammaCorrection(vec3 col)
{
    return pow(col, vec3(0.4545));
}