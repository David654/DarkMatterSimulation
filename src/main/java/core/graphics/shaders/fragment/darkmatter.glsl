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
    mat3 m = mat3( 0.00,  0.80,  0.60,
    -0.80,  0.36, -0.48,
    -0.60, -0.48,  0.64);
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
        vec3 darkMatterPos = p + uPositions[0];

        float darkMatterDist = fSphere(darkMatterPos, 10) + fbm(darkMatterPos * 0.9);
        float darkMatterID = -1;
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
    return res;
}