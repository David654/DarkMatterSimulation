vec4 rayMarch(vec3 ro, inout vec3 rd)
{
    vec3 initialRd = rd;
    vec2 hit;
    vec2 object = vec2(0, -2);
    vec3 p = ro;
    vec2 nearest = vec2(3.402823466e+38, -2);

    for(int i = 0; i < uMaxSteps; i++)
    {
        //vec3 p = ro + object.x * rd;
        hit = map(p);

        object.x += hit.x;
        object.y = hit.y;
        int index = int(object.y) - 1;


        //float theta = 2 * G * uMasses[int(object.y) - 1] / (hit.x * AU * pow(LIGHT_SPEED, 2));

        /*if(index == 0)
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

        float singularityDist = distance(p, uPositions[index] - uOffset);
        //hit.x = min(hit.x, singularityDist) * 2;
        float warpFactor = 1.0 / (pow(singularityDist, 2.0) + 0.000001);
        vec3 singularityVector = normalize(uPositions[index] - p);

        float warpAmount = 4 * G * uMasses[int(object.y) - 1] / (pow(10.0, 7.3) * pow(LIGHT_SPEED, 2.0)); // 5.0

        rd = normalize(rd + singularityVector * warpFactor * warpAmount);

        p += rd * hit.x;

        //dist = 0.1 / dist;

        if(uIDs[index] == 1 && hit.x < nearest.x)
        {
            nearest = vec2(hit.x, index);
        }

        if(abs(hit.x) < EPSILON || object.x > uMaxDist)
        {
            break;
        }
    }
    return vec4(object, nearest);
}