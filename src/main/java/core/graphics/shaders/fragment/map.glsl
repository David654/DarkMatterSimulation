vec2 map(vec3 p)
{
    vec2 res;

    res = mapBodies(p, false);
    //res = mapDarkMatter(p);
    //res = fOpUnionID(res, mapDarkMatter(p));

    return res;
}