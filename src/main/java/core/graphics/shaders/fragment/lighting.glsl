float getSoftShadow(vec3 p, vec3 lightPos)
{
    float res = 1.0;
    float dist = 0.01;
    float lightSize = 0.03;

    for(int i = 0; i < uMaxSteps; i++)
    {
        float hit = map(p + lightPos * dist).x;
        res = min(res, hit / (dist * lightSize));
        dist += hit;
        if(hit < EPSILON || dist > 60.0)
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

vec3 getLight(vec3 p, vec3 rd, float id, vec3 lightPos, vec3 bgColor)
{
    vec3 L = normalize(lightPos - p);
    vec3 N = getNormal(p);
    vec3 V = -rd;
    vec3 R = reflect(-L, N);

    vec3 col = getPlanet(p, id, N, bgColor);

    vec3 ambient = col * 0.1;
    vec3 diffuse = col * clamp(dot(L, N), 0.0, 1.0);
    vec3 specColor = vec3(0.1);
    vec3 specular = specColor * pow(clamp(dot(R, V), 0.0, 1.0), 5.0);
    vec3 fresnel = 0.25 * col * pow(1.0 + dot(rd, N), 3.0);

    // shadows
    float shadow = getSoftShadow(p + N * 0.02, L);

    // ambient occlusion
    float occ = getAmbientOcclusion(p, N);

    // reflections
    vec3 back = 0.05 * col * clamp(dot(N, -L), 0.0, 1.0);

    if(id == 1 || uEnableLighting == 0)
    {
        return col;
    }
    else
    {
        return (ambient + fresnel + back) * occ + (diffuse + specular * occ); // * shadow
    }
}

vec3 gammaCorrection(vec3 col)
{
    return pow(col, vec3(0.4545));
}