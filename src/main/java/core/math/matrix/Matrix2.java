package core.math.matrix;

import core.math.util.MathUtils;

public class Matrix2 implements Matrix<Matrix2>
{
    private final float[][] matrix;

    public Matrix2()
    {
        this(new float[2][2]);
    }

    public Matrix2(float[][] matrix)
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

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                sb.append(matrix[i][j]).append(i < 1 ? " | " : "");
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
        Matrix2 matrix = (Matrix2) object;

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                if(this.matrix[i][j] != matrix.get(i, j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix2 zero()
    {
        return new Matrix2();
    }

    public Matrix2 abs()
    {
        Matrix2 matrix = new Matrix2();

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                matrix.set(i, j, MathUtils.abs(this.matrix[i][j]));
            }
        }
        return matrix;
    }

    public Matrix2 min(Matrix2 matrix)
    {
        Matrix2 b = new Matrix2();

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0 ; j < 2; j++)
            {
                b.set(i, j, MathUtils.min(this.get(i, j), matrix.get(i, j)));
            }
        }
        return b;
    }

    public Matrix2 max(Matrix2 matrix)
    {
        Matrix2 b = new Matrix2();

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0 ; j < 2; j++)
            {
                b.set(i, j, MathUtils.max(this.get(i, j), matrix.get(i, j)));
            }
        }
        return b;
    }
}