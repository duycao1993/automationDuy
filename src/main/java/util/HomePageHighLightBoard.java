package util;

public enum HomePageHighLightBoard {
    TopHighLightLeft(0),
    TopHighLightRight(1),
    BottomHighLightLeft(2),
    BottomHighLightMid(3),
    BottomHighLightRight(4);

    private Integer locatorIndex;

    private HomePageHighLightBoard(Integer locatorIndex){
        this.locatorIndex = locatorIndex;
    }

    public Integer getIndex(){
        return this.locatorIndex;
    }
}
