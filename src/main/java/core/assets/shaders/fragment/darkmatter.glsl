float hash(float n)
{
    return fract(sin(n) * 43758.5453);
}

float noise(in vec3 x)
{
    vec3 p = floor(x);
    vec3 f = fract(x);

    f = f * f * (3.0 - 2.0 * f);

    float n = p.x + p.y * 57.0 + 113.0 * p.z;

    float res = mix(mix(mix(hash(n + 0.0), hash(n + 1.0), f.x),
    mix(hash(n + 57.0), hash(n + 58.0), f.x), f.y),
    mix(mix(hash(n + 113.0), hash(n + 114.0), f.x),
    mix(hash(n + 170.0), hash(n + 171.0), f.x), f.y), f.z);
    return res;
}

float fbm(vec3 p)
{
    mat3 m = mat3(0.00, 0.80, 0.60,
    -0.80, 0.36, -0.48,
    -0.60, -0.48, 0.64);
    float f;

    f = 0.5000 * noise(p);
    p = m * p * 2.02;
    f += 0.2500 * noise(p);
    p = m * p * 2.03;
    f += 0.1250 * noise(p);
    return f;
}

vec2 mapDarkMatter(vec3 p)
{
    vec2 res;

    for(int i = 0; i < uBodyNum; i++)
    {
        vec3 darkMatterPos = p + uPositions[i];
        float radius = (uDimensions[i].x + uDimensions[i].y + uDimensions[i].z) / 3 * 8;
        //darkMatterPos.xz = pMod2(darkMatterPos.xz, vec2(radius * 2));
        //pMod3(darkMatterPos, vec3(radius * 2));
        //pMirrorOctant(darkMatterPos.xz, vec2(radius + radius / 8));
        //pMod2(darkMatterPos.xz, vec2(radius * 2));

        float darkMatterDist = fEllipsoid(darkMatterPos, uDimensions[i] * 1.5) * pow(radius, -0.80702) - fbm(darkMatterPos * pow(radius, -0.817997) * (1 + uDarkMatterDensities[i] * 2.5)); //-0.593938
       // float darkMatterDist = radius - length(darkMatterPos) * 0.05 + fbm(darkMatterPos * 0.3); //-0.593938
        //float darkMatterDist2 = fEllipsoid(darkMatterPos, uDimensions[i] * 1.9) * pow(radius, -0.80702) - fbm(darkMatterPos * pow(radius, -0.817997) * (1 + uDarkMatterDensities[i] * 1.5)); //-0.593938
        //float darkMatterDist = max(darkMatterDist1, -darkMatterDist2);
       // float darkMatterDist1 = fBox(darkMatterPos, vec3(radius)) * 0.1 - fbm(darkMatterPos * pow(radius, -0.593938));
       // float darkMatterDist2 = fBox(darkMatterPos, vec3(radius  - radius / 8)) * 0.1 - fbm(darkMatterPos * pow(radius, -0.593938));
        //float darkMatterDist = fBox(darkMatterPos, vec3(radius)) * 0.02 / length(uPos) * 1000 - fbm(darkMatterPos * pow(radius, -0.593938));
        //float darkMatterDist = fPlane(darkMatterPos, vec3(0, 1, 0), 10.0) * 0.02 / length(uPos) * 1000 - fbm(darkMatterPos * pow(radius, -0.593938));
       // float darkMatterDist2 = fBox(darkMatterPos, vec3(uMaxDist - uMaxDist / 8)) * 0.02 / length(uPos) * 1000 - fbm(darkMatterPos * pow(radius, -0.593938));
        //float darkMatterDist = max(darkMatterDist1, -darkMatterDist2);
        /*float darkMatterDist2 = fBox(vec3(darkMatterPos.x + radius * 2, darkMatterPos.yz), vec3(radius)) * 0.01 / length(uPos) * 1000 - fbm(vec3(darkMatterPos.x + radius, darkMatterPos.yz) * pow(radius, -0.593938));
        float darkMatterDist3 = fBox(vec3(darkMatterPos.xy, darkMatterPos.z + radius * 2), vec3(radius)) * 0.01 / length(uPos) * 1000 - fbm(vec3(darkMatterPos.xy, darkMatterPos.z + radius) * pow(radius, -0.593938));
        float darkMatterDist4 = fBox(vec3(darkMatterPos.x + radius * 2, darkMatterPos.y, darkMatterPos.z + radius * 2), vec3(radius)) * 0.01 / length(uPos) * 1000 - fbm(vec3(darkMatterPos.x + radius, darkMatterPos.y, darkMatterPos.z + radius) * pow(radius, -0.593938));
        float darkMatterDist = min(darkMatterDist1, darkMatterDist2);
        darkMatterDist = min(darkMatterDist, darkMatterDist3);
        darkMatterDist = min(darkMatterDist, darkMatterDist4);**/
        float darkMatterID = -i - 1;
        vec2 darkMatter = vec2(darkMatterDist, darkMatterID);

        if(i == 0)
        {
            res = darkMatter;
        }
        else
        {
            res = fOpUnionID(res, darkMatter);
        }
    }
    return vec2(-res.x, res.y);
}