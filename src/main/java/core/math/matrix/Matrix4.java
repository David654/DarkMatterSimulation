package core.math.matrix;

import core.math.util.MathUtils;

public class Matrix4 implements Matrix<Matrix4>
{
    private final float[][] matrix;

    public Matrix4()
    {
        this(new float[4][4]);
    }

    public Matrix4(float[][] matrix)
    {
        this.matrix = matrix;
    }

    public float get(int i, int j)
    {
        return matrix[i][j];
    }

    public void set(int i, int j, float value)
    {
        matrix[i][j] = value;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                sb.append(matrix[i][j]).append(i < 3 ? " | " : "");
            }
            sb.append("\n--------");
        }

        return sb.toString();
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public boolean equals(Object object)
    {
        Matrix4 matrix = (Matrix4) object;

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(this.matrix[i][j] != matrix.get(i, j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix4 zero()
    {
        return new Matrix4();
    }

    public Matrix4 abs()
    {
        Matrix4 matrix = new Matrix4();

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                matrix.set(i, j, MathUtils.abs(this.matrix[i][j]));
            }
        }
        return matrix;
    }

    public Matrix4 min(Matrix4 matrix)
    {
        Matrix4 b = new Matrix4();

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0 ; j < 4; j++)
            {
                b.set(i, j, MathUtils.min(this.get(i, j), matrix.get(i, j)));
            }
        }
        return b;
    }

    public Matrix4 max(Matrix4 matrix)
    {
        Matrix4 b = new Matrix4();

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0 ; j < 4; j++)
            {
                b.set(i, j, MathUtils.max(this.get(i, j), matrix.get(i, j)));
            }
        }
        return b;
    }
}