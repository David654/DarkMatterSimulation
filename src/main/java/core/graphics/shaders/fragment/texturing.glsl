vec3 getNormal(vec3 p)
{
    vec2 e = vec2(EPSILON, 0.0);
    vec3 n = vec3(map(p).x) - vec3(map(p - e.xyy).x, map(p - e.yxy).x, map(p - e.yyx).x);
    return normalize(n);
}

vec3 triPlanar(sampler2D tex, vec3 p, vec3 normal)
{
    normal = abs(normal);
    normal *= pow(normal, vec3(5.0));
    normal /= normal.x + normal.y + normal.z;
    return (texture(tex, p.xy * 0.5 + 0.5) * normal.z + texture(tex, p.xz * 0.5 + 0.5) * normal.y + texture(tex, p.yz * 0.5 + 0.5) * normal.x).rgb;
}

vec3 textureSphere(sampler2D tex, vec3 pos)
{
    vec2 polarUV = vec2(atan(-pos.x, pos.z), -asin(pos.y) * 2.0) / PI * 0.5 + 0.5;
    return texture(tex, polarUV).rgb;
}

vec3 getPlanet(vec3 p, float id, vec3 normal, vec3 bgColor)
{
    vec3 planet = vec3(1, 0, 0);
    int index = int(id - 1);

    if(id == 0)
    {
        vec3 col = vec3(0);
        vec2 scp = sin(0.2 * 6.2831 * p.xz);

        vec3 wir = vec3(0.0);
        wir += 1.0 * exp(-12.0 * abs(scp.x));
        wir += 1.0 * exp(-12.0 * abs(scp.y));
        wir += 0.5 * exp(-4.0 * abs(scp.x));
        wir += 0.5 * exp(-4.0 * abs(scp.y));
        wir *= 0.2 + 1.0;
       // wir *= 1.0 / (length(p.xz)) + 0.3;

        col += wir;
        return bgColor + col;
    }

    vec3 pos = normalize(p + uPositions[index]);

    rotateZ(pos, uAxisInclinations[index]);
    rotateZ(normal, uAxisInclinations[index]);
    pR(pos.xz, -uRotationSpeeds[index] * uTime);
    pR(normal.xz, -uRotationSpeeds[index] * uTime);

    vec3 ringPos = normalize((p + uPositions[index]));
    pR(ringPos.xz, -uRotationSpeeds[index] * uTime);
    planet = id - index == 1.0 ? textureSphere(uTextures[index], pos) : triPlanar(uTextures[index], ringPos, normal);

    return planet;
}