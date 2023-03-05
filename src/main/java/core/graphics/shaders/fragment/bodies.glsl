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

vec2 mapStars(vec3 p)
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
            if(uIDs[i] == 1 && uVisibilities[i] == 1)
            {
                vec3 bodyPos = p + uPositions[i];
                bodyPos.y = -10;
                float bodyDist = fEllipsoid(bodyPos, uDimensions[i]);
                vec2 body = vec2(bodyDist, -2);

                res = fOpDifferenceRoundID(res, body, pow(uMasses[i], 1.0 / 20.0));
            }
        }
    }

    for(int i = 0; i < uBodyNum; i++)
    {
        if(uIDs[i] == 1 && uVisibilities[i] == 1)
        {
            vec3 bodyPos = p + uPositions[i];
            bodyPos = rotateZ(bodyPos, uAxisInclinations[i]);
            pR(bodyPos.xz, -uRotationSpeeds[i] * uTime);
            //bodyPos.y = pModSingle1(-bodyPos.y, 20);

            float bump = (uBump[i] == 1 ? bumpMapping(uBumpTextures[i], normalize(bodyPos), 1.0) : 0);
            float bodyDist = fEllipsoid(bodyPos, uDimensions[i] + vec3(bump));
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
    }

    return res;
}

vec2 mapBodies(vec3 p, bool isBump)
{
    vec2 res = mapStars(p);

    for(int i = 0; i < uBodyNum; i++)
    {
        if(uIDs[i] != 1 && uVisibilities[i] == 1)
        {
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

            vec3 bodyPos = p + uPositions[i];
            bodyPos = rotateZ(bodyPos, uAxisInclinations[i]);
            pR(bodyPos.xz, -uRotationSpeeds[i] * uTime);
            //pMirror(bodyPos.y, length(uDimensions[i]));
            //bodyPos.y = pModSingle1(-bodyPos.y, 20);

            float bump = 0;
            if(isBump)
            {
                bump = (uBump[i] == 1 ? bumpMapping(uBumpTextures[i], normalize(bodyPos), pow(PI / uDimensions[i].x, 4)) : 0);
            }
            float bodyDist = fEllipsoid(bodyPos, uDimensions[i] + vec3(bump)); //  * 0.05 + fbm(bodyPos * 0.3)
            //bodyDist += fbm(bodyPos * 0.5); // + 10 * pow(uTime, 2 / uTime)
            //bodyDist += cosNoise(p.xz * cos(uTime));
            vec2 body = vec2(bodyDist, i + 1);

            if(length(res) == 0)
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
    }

    return res;
}