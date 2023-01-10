float wave(vec2 p, vec2 emitter, float speed, float phase, float timeshift)
{
    float dst = distance(p, emitter);
    float time = uTime * 0.1;
    return pow(E, sin(dst * phase - (time + timeshift) * speed));
}

float cosNoise(vec2 p)
{
    return 0.5 * (sin(p.x) + sin(p.y));
}

vec2 mapBodies(vec3 p)
{
    vec2 res;

    if(uShowGrid == 1)
    {
        float planeDist = fPlane(p, vec3(0, 1, 0), 10.0);
        float planeID = 0;
        vec2 plane = vec2(planeDist, planeID);
        res = plane;

        for(int i = 0; i < uBodyNum; i++)
        {
            vec3 bodyPos = p + uPositions[i];
            bodyPos.y = -10;
            float bodyDist = fEllipsoid(bodyPos, uDimensions[i]);
            vec2 body = vec2(bodyDist, -2);

            res = fOpDifferenceRoundID(res, body, pow(uMasses[i], 1.0 / 20.0));
        }
    }

    for(int i = 0; i < uBodyNum; i++)
    {
        vec3 bodyPos = p + uPositions[i];
        bodyPos = rotateZ(bodyPos, uAxisInclinations[i]);
        pR(bodyPos.xz, -uRotationSpeeds[i] * uTime);
        //bodyPos.y = pModSingle1(-bodyPos.y, 20);

        float bodyDist = fEllipsoid(bodyPos, uDimensions[i]); //  * 0.05 + fbm(bodyPos * 0.3)

        if(uIDs[i] == 1)
        {
            bodyDist += fbm(bodyPos * 0.9 + 10 * pow(uTime, 2 / uTime));
            //bodyDist += cosNoise(p.xz * cos(uTime));
        }

        if(uBump[i] == 1)
        {
            bodyDist += bumpMapping(uBumpTextures[i], bodyPos, bodyPos + 0.9, bodyDist, 0.9);
            bodyDist += 0.9;
        }
        vec2 body = vec2(bodyDist, i + 1);



        if(i == 0)
        {
            res = uShowGrid == 1 ? fOpUnionID(res, body) : body;
        }
        else
        {
            res = fOpUnionID(res, body);
        }

        if(uRings[i] == 1)
        {
            float ringDist1 = fSphere(bodyPos, uRingRadiuses[i].x);
            float ringDist2 = fCylinder(bodyPos, uRingRadiuses[i].y, 0.01); // 0.001
            float ringID = i + 1 + 0.1;
            vec2 ring1 = vec2(ringDist1, ringID);
            vec2 ring2 = vec2(ringDist2, ringID);
            res = fOpUnionID(res, fOpDifferenceID(ring2, ring1));
        }
    }

    return res;
}