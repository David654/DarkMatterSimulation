#version 460

const float PI = 3.1415926;
const float EPSILON = 0.0001;
const int MAX_BODY_NUM = 10;

uniform vec2 uResolution;
uniform vec3 uCameraPos;
uniform float uMaxDist;
uniform float uMaxSteps;
uniform float uFov;
uniform vec2 uMousePos;
uniform float uTime;
uniform vec3 uPos;

uniform vec3 uPositions[MAX_BODY_NUM];
uniform float uRadiuses[MAX_BODY_NUM];
uniform float uRotationSpeeds[MAX_BODY_NUM];
uniform float uAxisInclinations[MAX_BODY_NUM];
uniform sampler2D uTextures[MAX_BODY_NUM];
uniform int uRings[MAX_BODY_NUM];
uniform vec2 uRingRadiuses[MAX_BODY_NUM];

uniform vec3 uDarkMatterPositions[MAX_BODY_NUM];
uniform float uDarkMatterRadiuses1[MAX_BODY_NUM];
uniform float uDarkMatterRadiuses2[MAX_BODY_NUM];
uniform float uDarkMatterDensities[MAX_BODY_NUM];

uniform sampler2D uRingTextures[MAX_BODY_NUM];
uniform sampler2D uBackgroundTexture;

uniform int uEnableLighting;
uniform int uShowGrid;