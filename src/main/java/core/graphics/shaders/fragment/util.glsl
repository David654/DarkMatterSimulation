vec2 fOpUnionID(vec2 res1, vec2 res2)
{
    return res1.x < res2.x ? res1 : res2;
}

vec2 fOpUnionSoftID(vec2 res1, vec2 res2, float r)
{
    float dist = fOpUnionSoft(res1.x, res2.x, r);
    return res1.x < res2.x ? vec2(dist, res1.y) : vec2(dist, res2.y);
}

vec2 fOpDifferenceID(vec2 res1, vec2 res2)
{
    return res1.x > -res2.x ? res1 : vec2(-res2.x, res2.y);
}

vec2 fOpDifferenceRoundID(vec2 res1, vec2 res2, float r)
{
    float dist = fOpDifferenceRound(res1.x, res2.x, r);
    return res1.x < res2.x ? vec2(dist, res1.y) : vec2(dist, res2.y);
}

void rotateSphere(inout vec3 p, float f)
{
    pR(p.xz, f);
}

vec3 rotateX(in vec3 v, float theta)
{
    float sin = sin(theta);
    float cos = cos(theta);
    return v *= mat3(1, 0, 0, 0, cos, -sin, 0, sin, cos);
}

vec3 rotateY(in vec3 v, float theta)
{
    float sin = sin(theta);
    float cos = cos(theta);
    return v *= mat3(cos, 0, sin, 0, 1, 0, -sin, 0, cos);
}

vec3 rotateZ(in vec3 v, float theta)
{
    float sin = sin(theta);
    float cos = cos(theta);
    return v *= mat3(cos, -sin, 0, sin, cos, 0, 0, 0, 1);
}