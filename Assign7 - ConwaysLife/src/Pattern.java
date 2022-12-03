public abstract class Pattern {         /*Abstract means that no Pattern object can be created. To access it, it must be
                                        inherited from another class.*/
    public abstract int getSizeX();
    public abstract int getSizeY();
    public abstract boolean getCell(int x, int y);


}
