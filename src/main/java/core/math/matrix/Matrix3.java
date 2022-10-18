package core.math.matrix;

import core.math.util.MathUtils;

public class Matrix3 implements Matrix<Matrix3>
{
    private final float[][] matrix;

    public Matrix3()
    {
        this(new float[3][3]);
    }

    public Matrix3(float[][] matrix)
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

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                sb.append(matrix[i][j]).append(i < 2 ? " | " : "");
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
        Matrix3 matrix = (Matrix3) object;

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(this.matrix[i][j] != matrix.get(i, j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix3 zero()
    {
        return new Matrix3();
    }

    public Matrix3 abs()
    {
        Matrix3 matrix = new Matrix3();

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                matrix.set(i, j, MathUtils.abs(this.matrix[i][j]));
            }
        }
        return matrix;
    }

    public Matrix3 min(Matrix3 matrix)
    {
        Matrix3 b = new Matrix3();

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0 ; j < 3; j++)
            {
                b.set(i, j, MathUtils.min(this.get(i, j), matrix.get(i, j)));
            }
        }
        return b;
    }

    public Matrix3 max(Matrix3 matrix)
    {
        Matrix3 b = new Matrix3();

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0 ; j < 3; j++)
            {
                b.set(i, j, MathUtils.max(this.get(i, j), matrix.get(i, j)));
            }
        }
        return b;
    }
}
