

public class Grid {

    private int x = 0;
    private int y = 0;
    private int value = -1;
    private boolean isChangable = true;
    private boolean isSolved = false;
    private int[] possibleValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};


    public Grid() {}
    public Grid(int _x, int _y, int _value, boolean _isChangable) throws Exception{
        setX(_x);
        setY(_y);
        setValue(_value);
        setIsChangable(_isChangable);

        if (!isChangable) setIsSolved(true);

        for (int i = 0; i < 9; i++) {
            if (_value != possibleValues[i]) {
                RemovePossibleIndex(i);
            }
        }
    }
    public Grid(int _x, int _y) throws Exception{
        setX(_x);
        setY(_y);
    }


    public boolean isValidValue(int _value) {
        return _value > 0 && _value < 10;
    }
    public boolean isValidCoordinate(int _index) {
        return _index >= 0 && _index <= 8;
    }

    public void setX(int _x) throws Exception {
        if (!isValidCoordinate(_x))
            throw new Exception("The Coordinate of the grid must be integer between 0~8");

        x = _x;

    }
    public void setY(int _y) throws Exception{
        if (!isValidCoordinate(_y))
            throw new Exception("The Coordinate of the grid must be integer between 0~8");

        y = _y;
    }
    public void setValue(int _value) throws Exception{
        if (value != -1)
            throw new Exception("There is already a value in the grid");

        if (!isValidValue(_value))
            throw new Exception("The value in the grid must be integer between 1~9");

        value = _value;
    }

    public void setIsChangable(boolean _isChangable) { isChangable = _isChangable; }
    public void setIsSolved(boolean _isSolved) { isSolved = _isSolved; }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getValue() { return value; }
    public boolean getIsChangable() { return isChangable; }
    public boolean getIsSolved() { return isSolved; }
    public int[] getPossibleValues() { return possibleValues; }

    public void RemovePossible(int possibleValue) throws Exception {
        for (int i = 0; i < 9; i++) {
            if (possibleValue == possibleValues[i]) {
                possibleValues[i] = -1;
            }
        }
        Solved();
    }
    public void RemovePossibleIndex(int index) throws Exception{
        possibleValues[index] = -1;
        Solved();
    }

    public void Solved() throws Exception{

        int possCnt = 0;
        int possV = -1;

        for  (int i: possibleValues) {
            if (i != -1) {
                possCnt++;
                possV = i;
            }
        }

        if (possCnt == 1) {
            if (value == -1) {
                setIsSolved(true);
                setValue(possV);
            }
            else if (value == possV)
                setIsSolved(true);
            else if (value != possV)
                throw new Exception("Something WRONG. The only possible value is not matching the grid value");
        }
        else if (possCnt == 0) {
            throw new Exception("There is no solution");
        }
    }


}
