vec3 triPlanar(sampler2D tex, vec3 p, vec3 normal, vec3 bgColor)
{
    normal = abs(normal);
    normal *= pow(normal, vec3(5.0));
    normal /= normal.x + normal.y + normal.z;
    vec3 col = (texture(tex, p.xy * 0.5 + 0.5) * normal.z + texture(tex, p.xz * 0.5 + 0.5) * normal.y + texture(tex, p.yz * 0.5 + 0.5) * normal.x).rgb;
    return col;
}

vec3 textureSphere(sampler2D tex, vec3 pos)
{
    vec2 polarUV = vec2(atan(-pos.x, pos.z), -asin(pos.y) * 2.0) / PI * 0.5 + 0.5;
    return texture(tex, polarUV).rgb;
}

vec3 textureRing(sampler2D tex, vec3 pos, vec2 radiuses)
{
    /*float radius = length(pos);
    float angle = atan(-pos.x, pos.z);
    vec2 polarUV = vec2((radius - radiuses.x) / (radiuses.y - radiuses.x), angle) / PI * 0.5 + 0.5;**/

    float radius = length(pos);
    float angle = atan(-pos.x, pos.z);
    vec2 polarUV = vec2((radius) / (radiuses.y), angle * 0.5 / PI + 0.5);

    return texture(tex, polarUV).rgb;
}

float bumpMapping(sampler2D tex, vec3 pos, vec3 n, float dist, float factor)
{
    float bump = 0.0;
    if(dist < 0.1)
    {
        vec3 normal = normalize(n);
        bump += factor * textureSphere(tex, pos).r;
    }

    return bump;
}

vec3 getPlanet(vec3 p, float id, vec3 normal, vec3 bgColor)
{
    vec3 planet = vec3(0, 0, 0);
    int index = int(id - 1);

    if(id == 0)
    {
        vec3 col = vec3(0);
        vec2 scp = sin(0.15 * PI * 2 * p.xz);
        vec3 wir = vec3(0.0);
        wir += 1.0 * exp(-12.0 * abs(scp.x));
        wir += 1.0 * exp(-12.0 * abs(scp.y));
        wir += 0.5 * exp(-4.0 * abs(scp.x));
        wir += 0.5 * exp(-4.0 * abs(scp.y));
        wir *= 0.2 + 1.0;
       // wir *= 0.1 / length(normalize(p - uPositions[index])) + 0.05;

        col += wir;
        col = mix(bgColor, col, 0.6);
        return bgColor + col;
    }

    if(id == -1)
    {
        vec3 col = vec3(1);
        return col;
    }

    vec3 pos = normalize(p + uPositions[index]);

    if(uIDs[index] == 1)
    {
        float waveSize = 0.01;
        float ripple = 50.0;
       // pos.y -= sin(((uTime * 0.1) + pos.x) * ripple) * waveSize;
        //We do the reverse for x. I used cosine instead to make the uv.y and uv.x sync differently
       // pos.x -= cos(((uTime * 0.1) + pos.y) * ripple) * waveSize;

    }

    pos = rotateZ(pos, uAxisInclinations[index]);
    normal = rotateZ(normal, uAxisInclinations[index]);
    pR(pos.xz, -uRotationSpeeds[index] * uTime);
    pR(normal.xz, -uRotationSpeeds[index] * uTime);

    float scale = 0.9897 / uRingRadiuses[index].y;
    vec3 ringPos = (p + uPositions[index]) * scale;
    ringPos = rotateZ(ringPos, uAxisInclinations[index]);
    pR(ringPos.xz, -uRotationSpeeds[index] * uTime);

    planet = id - index == 1.0 ? textureSphere(uTextures[index], pos) : triPlanar(uRingTextures[index], ringPos, normal, bgColor); // triPlanar(uRingTextures[index], ringPos, normal, bgColor)

    /*if(id == 1)
    {
        planet = vec3(0);
    }

    if(id == 1.1)
    {
        planet = vec3(1);
    }**/
    //textureRing(uRingTextures[index + 1], ringPos, normalize(uRingRadiuses[index]))
    //planet = triPlanar(uTextures[index], pos, normal);

    return planet;
}