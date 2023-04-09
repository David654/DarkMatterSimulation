out vec4 outCol;

mat3 getCam(vec3 ro, vec3 lookAt)
{
    vec3 camF = normalize(vec3(lookAt - ro));
    vec3 camR = normalize(cross(vec3(0, 1, 0), camF));
    vec3 camU = cross(camF, camR);
    return mat3(camR, camU, camF);
}

vec3 mouseControl(in vec3 ro)
{
    vec2 m = uMousePos / uResolution - 0.5;
    vec3 p = uPositions[uSelectedBodyIndex];
    mat3 translate = mat3(1, 0, -p.x, 0, 1, -p.y, 0, 0, -p.z);
    //ro *= translate;
    //ro = rotateX(ro - p, m.y * PI * 0.5 - 0.5) + p;
    //ro = rotateY(ro - p, m.x * 2 * PI) + p;
    ro = rotateX(ro, m.y * PI * 0.5 - 0.5);
    ro = rotateY(ro, m.x * 2 * PI);
    //ro *= inverse(translate);
    return ro;
    //pR(ro.yz, ); // x axis
    //pR(ro.xz, ); // y axis
}

vec3 getSky(in vec3 rd)
{
    rd = rotateX(rd, 3 * PI / 2);
    rd = rotateZ(rd, 3 * PI / 2);

    vec2 skyUV = vec2(atan(rd.x, rd.y), asin(rd.z) * 2.0) / PI * 0.5 + 0.5;
    return texture(uBackgroundTexture, skyUV).rgb;
}

vec3 render(in vec2 uv)
{
    vec3 ro = uPos;

    ro = mouseControl(ro);

    vec3 lookAt = vec3(0);
    vec3 rd = getCam(ro, lookAt) * normalize(vec3(uv, uFov));

    vec3 lightPos[BODY_NUM_LIMIT];

    int lightCount = 0;

    for(int i = 0; i < uBodyNum; i++)
    {
        if(uIDs[i] == 1)
        {
            lightPos[lightCount] = -uPositions[i];
            lightCount++;
        }
    }

    if(lightCount == 0)
    {
        vec3 pos = vec3(1000, 0, 0);
       // pR(pos.xz, -uRotationSpeeds[0] * uTime);
        lightPos[0] = pos;
    }

    vec4 object[4] = rayMarch(ro, rd, lightPos, getSky(rd));
    vec3 col;

    if(object[0].x < uMaxDist)
    {
        //rd = getCam(ro, lookAt) * normalize(vec3(uv, uFov));
        vec3 p = ro + object[0].x * rd;

        col = getLight(p, rd, object[0].y, lightPos, getSky(rd));

        if(uIDs[int(object[0].y) - 1] == 1)
        {
            vec3 accentCol = uColors[int(object[1].y)];
            vec3 gray = vec3((col.r + col.g + col.b) / 3.0);
            vec3 diff = vec3(0.5) - gray;
            col = accentCol - diff;
            col = clamp(col, vec3(0), vec3(1));
        }

        //vec4 antialiasing = getAntialiasing(p, ro, rd, lightPos, col, getSky(rd));
        //col = mix(col, antialiasing.xyz / (0.001 + antialiasing.w), antialiasing.w);

        //if(object.y == 0)
        //{
            //vec3 planetPos = p + uPositions[0];
            //pR(planetPos.xz, -uRotationSpeeds[0] * uTime);

            //col += 1.0 - exp(-col);
       // }

        //if(object.y == -1)
        //{
            //float tmp = density / float(sampleCount);
        //}
    }
    else
    {
        col = getSky(rd);
        //vec2 pos = uv + normalize(lightPos).xy;

        //float d = abs(fSphere(uPositions[0], uRadiuses[0] * 2));
        //vec3 sun = vec3(1, 0.5, 0) * (1.0 - 0.1 * smoothstep(0.2, 0.5, 0.5));
        //col += 0.8 * sun * exp(-4.0 * d) * vec3(1.1, 1.0, 0.8);
        //col += 0.2 * sun * exp(-2.0 * d);
    }

    col += object[1].rgb;

    col += object[3].rgb;

    vec4 tmp = object[2];
    col = mix(col, tmp.xyz / (0.001 + tmp.w), tmp.w);

    //col = vec3(2 * object[0].z / 64, 0, 0);

    return col;
}

vec2 getUV(vec2 offset)
{
    return (2.0 * gl_FragCoord.xy + offset - uResolution.xy) / uResolution.y;
}

vec3 renderAAx1()
{
    return render(getUV(vec2(0)));
}

vec3 renderAAx2()
{
    float bxy = int(gl_FragCoord.x + gl_FragCoord.y) & 1;
    float nbxy = 1. - bxy;
    vec3 colAA = (render(getUV(vec2(0.33 * nbxy, 0.))) + render(getUV(vec2(0.33 * bxy, 0.66))));
    return colAA / 2.0;
}

vec3 renderAAx3()
{
    float bxy = int(gl_FragCoord.x + gl_FragCoord.y) & 1;
    float nbxy = 1. - bxy;
    vec3 colAA = (render(getUV(vec2(0.66 * nbxy, 0.))) + render(getUV(vec2(0.66 * bxy, 0.66))) + render(getUV(vec2(0.33, 0.33))));
    return colAA / 3.0;
}

vec3 renderAAx4()
{
    vec4 e = vec4(0.125, -0.125, 0.375, -0.375);
    vec3 colAA = render(getUV(e.xz)) + render(getUV(e.yw)) + render(getUV(e.wx)) + render(getUV(e.zy));
    return colAA /= 4.0;
}

void main()
{
    vec3 col = renderAAx1();
    //col = gammaCorrection(col);
    outCol = vec4(col, 1.0);
}