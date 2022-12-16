out vec4 outCol;

mat3 getCam(vec3 ro, vec3 lookAt)
{
    vec3 camF = normalize(vec3(lookAt - ro));
    vec3 camR = normalize(cross(vec3(0, 1, 0), camF));
    vec3 camU = cross(camF, camR);
    return mat3(camR, camU, camF);
}

void mouseControl(inout vec3 ro)
{
    vec2 m = uMousePos / uResolution - 0.5;
    pR(ro.yz, m.y * PI * 0.5 - 0.5); // x axis
    pR(ro.xz, m.x * 2 * PI); // y axis
}

vec3 getSky(in vec3 rd)
{
    rotateX(rd, 3 * PI / 2);
    rotateZ(rd, 3 * PI / 2);

    vec2 skyUV = vec2(atan(rd.x, rd.y), asin(rd.z) * 2.0) / PI * 0.5 + 0.5;
    return texture(uBackgroundTexture, skyUV).rgb;
}

vec3 render(in vec2 uv)
{
    vec3 ro = uPos;
    mouseControl(ro);
    vec3 lookAt = vec3(0, 0, 0);
    vec3 rd = getCam(ro, lookAt) * normalize(vec3(uv, uFov));

    vec3 col;
    vec3 lightPos = vec3(0, 0, 0);
    vec2 object = rayMarch(ro, rd);
    vec3 background = vec3(0.53, 0.81, 0.92);
    float sun = clamp(dot(lightPos, rd), 0.0, 1.0);

    if(object.x < uMaxDist)
    {
        vec3 p = ro + object.x * rd;
        col += getLight(p, rd, object.y, lightPos, getSky(rd));

        if(object.y == 0)
        {
            vec3 planetPos = p + uPositions[0];
            pR(planetPos.xz, -uRotationSpeeds[0] * uTime);

            //col += 1.0 - exp(-col);
        }

        if(object.y == -1)
        {
            //float tmp = density / float(sampleCount);
        }
    }
    else
    {
        col = getSky(rd);
        vec2 pos = uv + normalize(lightPos).xy;

        //float d = abs(fSphere(uPositions[0], uRadiuses[0] * 2));
        //vec3 sun = vec3(1, 0.5, 0) * (1.0 - 0.1 * smoothstep(0.2, 0.5, 0.5));
        //col += 0.8 * sun * exp(-4.0 * d) * vec3(1.1, 1.0, 0.8);
        //col += 0.2 * sun * exp(-2.0 * d);
    }

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