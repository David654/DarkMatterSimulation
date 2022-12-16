vec2 rayMarch(vec3 ro, vec3 rd)
{
    vec2 hit, object;
    for(int i = 0; i < uMaxSteps; i++)
    {
        vec3 p = ro + object.x * rd;
        hit = map(p);
        object.x += hit.x;
        object.y = hit.y;



        if(abs(hit.x) < EPSILON || object.x > uMaxDist)
        {
            break;
        }
    }
    return object;
}