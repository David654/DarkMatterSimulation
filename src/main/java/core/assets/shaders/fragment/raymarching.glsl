vec4[4] rayMarch(vec3 ro, inout vec3 rd, vec3[BODY_NUM_LIMIT] lightPos, vec3 sky)
{
    vec3 initialRd = rd;
    vec2 hit;
    vec2 object;
    vec2 star;
    vec3 p = ro;
    vec2 nearest = vec2(3.402823466e+38, -2);
    vec3 glowColor = vec3(0);

    vec3 darkMatterColor = vec3(0);
    vec3 darkMatterP = ro;
    vec2 darkMatterHit = vec2(0);
    // Transmittance
    float T = 1.0;

    // Raymarching for stars.
    for(int i = 0; i < 32; i++)
    {
        hit = mapStars(p);
        star.x += hit.x;
        star.y = hit.y;
        int index = int(star.y) - 1;

        if(hit.x < nearest.x)
        {
            nearest = vec2(hit.x, index);
        }

        if(abs(hit.x) < EPSILON || star.x > uMaxDist)
        {
            break;
        }

        p += rd * hit.x;
    }
    int steps = 0;

    if(uEnableDarkMatter == 1)
    {
        p = ro;
        hit = vec2(0);
        int rayMarchingSteps = 64;
        float dist = 0;
        float zStep = length(ro) * 2 / float(rayMarchingSteps);

        // Raymarching for dark matter.
        for(int i = 0; i < rayMarchingSteps; i++)
        {
            hit = mapDarkMatter(p);
            dist += -hit.x;

            if(hit.x > 0.0)
            {
                // hit.x = min(hit.x, 1);
                int index = int(abs(hit.y) - 1);
                // Substantially transparency parameter.
                //float absorption = 150.0 * (1 + 150 - uDarkMatterDensities[index] * 150);
                float absorption = 100.0;

                float tmp = hit.x / float(rayMarchingSteps);
                T *= 1.0 - (tmp * absorption);

                //darkMatterColor = vec3(abs(T));

                if(T <= 0.01)
                {
                    break;
                }

                // Add ambient + light scattering color
                float opacity = 50.0;
                float k = opacity * tmp * T;
                //vec3 cloudColor = vec3(0.37, 0.76, 0.95);
                vec3 cloudColor = uDarkMatterColors[index];
                vec3 col1 = cloudColor * k;

                darkMatterColor += col1;
                steps++;
            }

            p += rd * zStep;
        }
    }

    p = ro;
    hit = vec2(0);

    vec2 oh = vec2(0.0);
    vec4 tmp = vec4(0.0);

    for(int i = 0; i < uMaxSteps; i++)
    {
        // Basic raymarching stuff.
        //vec3 p = ro + object.x * rd;
        hit = map(p);

        float px = 2.0 / uResolution.y;
        float th1 = px * object.x;
        th1 *= 1.5;

        object.x += hit.x;
        object.y = hit.y;
        int index = int(object.y) - 1;

        //float theta = 2 * G * uMasses[int(object.y) - 1] / (hit.x * AU * pow(LIGHT_SPEED, 2));

        /* if(index == 0)
         {
             vec3 dir = normalize(uPositions[0] - p);
             float dist = distance(p, uPositions[0]);
             hit.x = min(hit.x, dist);
             float theta = 4 * G * uMasses[0] / (pow(10.0, 7.3) * pow(LIGHT_SPEED, 2.0));
             theta = clamp(theta, 0.0, 0.2);
             //dist = 0.1 / dist;

             dist = pow(dist + 1.0, 2.0);
             dist = hit.x * 1.0 / dist;
             rd = mix(rd, dir, dist);
         }**/

        // Gravitational lensing.
        float singularityDist = distance(p, uPositions[index] - uOffset);
        //hit.x = min(hit.x, singularityDist) * 2;
        float warpFactor = 1.0 / (pow(singularityDist, 2.0) + 0.000001);
        vec3 singularityVector = normalize(uPositions[index] - p);

        float warpAmount = 4 * G * uMasses[int(object.y) - 1] / (pow(10.0, 7.3) * pow(LIGHT_SPEED, 2.0)); // 5.0

        rd = normalize(rd + singularityVector * warpFactor * warpAmount);

        p += rd * hit.x;
        // steps++;

        //dist = 0.1 / dist;

        if(hit.x < 0 || object.x > uMaxDist)
        {
            break;
        }

        if(abs(hit.x) < th1)
        {
            // darkMatterColor *= clamp(dot(normalize(uPositions[int(object.y) - 1] - p), getNormal(p)), 0.0, 1.0);

            break;
        }

        // Antialiasing.
        float th2 = px * object.x * 2.0;
        if(T <= EPSILON && ((hit.x < th2) && (hit.x > oh.x)))
        {
            float lalp = 1.0 - (hit.x - th1) / (th2 - th1);
            vec3 lcol = getLight(p, rd, object.y, lightPos, sky) + getGlow(uColors[index], nearest.x, uApparentMagnitudes[index]) + darkMatterColor;
            tmp.xyz += (1.0 - tmp.w) * lalp * lcol;
            tmp.w += (1.0 - tmp.w) * lalp;
            if(tmp.w > 0.99)
            {
                break;
            }
        }
        oh = hit;
    }

    glowColor = getGlow(uColors[int(nearest.y)], nearest.x, uApparentMagnitudes[int(nearest.y)]);
    if(uIDs[int(object.y) - 1] != 1 && object[0].x < uMaxDist)
    {
        glowColor *= clamp(dot(normalize(uPositions[int(object.y) - 1] - p), getNormal(p)), 0.0, 1.0);
    }

    vec4[4] res = {vec4(object, steps, 0), vec4(glowColor, 0), tmp, vec4(clamp(darkMatterColor, 0.0, 1.0), 1)};
    return res;
}