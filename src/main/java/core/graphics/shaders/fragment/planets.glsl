vec2 mapPlanets(vec3 p)
{
    vec2 res;

    if(uShowGrid == 1)
    {
        float planeDist = fPlane(p, vec3(0, 1, 0), 10.0);
        float planeID = 0;
        vec2 plane = vec2(planeDist, planeID);
        res = plane;
    }

    for(int i = 0; i < MAX_BODY_NUM; i++)
    {
        vec3 planetPos = p + uPositions[i];
        pR(planetPos.xz, -uRotationSpeeds[i] * uTime);

        float planetDist = fSphere(planetPos, uRadiuses[i]);
        float planetID = i + 1;
        vec2 planet = vec2(planetDist, planetID);

        if(i == 0)
        {
            res = uShowGrid == 0 ? planet : fOpUnionID(res, planet);
        }
        else
        {
            //res = fOpDifferenceRoundID(res, planet, 20);
            res = fOpUnionID(res, planet);
        }

        /*if(uRings[i] == 1)
        {
            float ringDist1 = fSphere(planetPos, uRingRadiuses[i].x);
            float ringDist2 = fCylinder(planetPos, uRingRadiuses[i].y, 0.01);
            float ringID = i + 0.1;
            vec2 ring1 = vec2(ringDist1, ringID);
            vec2 ring2 = vec2(ringDist2, ringID);
            res = fOpUnionID(res, fOpDifferenceID(ring2, ring1));
        }**/
    }
    return res;
}