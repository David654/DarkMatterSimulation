vec2 map(vec3 p)
{
    vec2 res;

    res = mapPlanets(p);
    //res = fOpUnionID(res, mapDarkMatter(p));

    return res;
}