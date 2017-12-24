import java.util.*;

public class GameState {
    public int[][] state; //state of the puzzle
    public GameState parent; //parent in the game tree

    public GameState() {
        //initialize state to zeros, parent to null
        state = new int[3][3];
        for(int i = 0; i<state.length; i++){
            for(int j = 0; j<state[i].length; j++){
                state[i][j] = 0;
            }
        }

        parent = null;
    }

    public GameState(int[][] state) {
        //initialize this.state to state, parent to null
        this.state = new int[3][3];

        for(int i = 0; i<state.length; i++){
            for(int j = 0; j<state[i].length;j++){
                this.state[i][j] = state[i][j];
            }
        }
        parent = null;
    }

    public GameState(int[][] state, GameState parent) {
        //initialize this.state to state, this.parent to parent
        this.state = new int[3][3];

        for(int i = 0; i<state.length; i++){
            for(int j = 0; j<state[i].length;j++){
                this.state[i][j] = state[i][j];
            }
        }
        this.parent = parent;
    }

    public GameState swapRight(GameState s, int row, int col) {
        //helper function to swap blank space with right block
        GameState rGameState = new GameState(s.state, s);

               int rightBlock = s.state[row][col + 1];
               int blankSpace = rGameState.state[row][col];

               rGameState.state[row][col] = rightBlock;
               rGameState.state[row][col + 1] = blankSpace;

        return rGameState;
    }

    public GameState swapLeft(GameState s, int row, int col) {
        //helper function to swap blank space with left block
        GameState lGameState = new GameState(s.state, s);

            int leftBlock = s.state[row][col-1];
            int blankSpace = lGameState.state[row][col];

            lGameState.state[row][col] = leftBlock;
            lGameState.state[row][col-1] = blankSpace;

        return lGameState;
    }

    public GameState swapUp(GameState s, int row, int col) {
        //helper function to swap blank space with up block
        GameState uGameState = new GameState(s.state, s);

            int upBlock = uGameState.state[row-1][col];
            int blankSpace = uGameState.state[row][col];

            uGameState.state[row][col] = upBlock;
            uGameState.state[row-1][col] = blankSpace;

        return uGameState;
    }

    public GameState swapDown(GameState s, int row, int col) {
        //helper function to swap blank space with down block
        GameState dGameState = new GameState(s.state, s);

            int downBlock = dGameState.state[row+1][col];
            int blankSpace = dGameState.state[row][col];

            dGameState.state[row][col] = downBlock;
            dGameState.state[row+1][col] = blankSpace;

        return dGameState;
    }

    public boolean isEnd() {
        //helper function to check if the GameState is the end state e.g.
        //0 1 2
        //3 4 5
        //6 7 8
        boolean isFinal = true;
        int value = 0;
        for(int i = 0; i<state.length; i++){
            for(int j = 0; j<state[i].length; j++){
                if(state[i][j] != value)
                    isFinal = false;
                value++;
            }
        }
        return isFinal;
    }

    public ArrayList<GameState> getAdjacent() {
        //Use the swap functions to generate the new vertices of the tree
        //Beware of the boundary conditions, i.e. don’t swap left when you are
        //already on the left edge
        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;

        int row = 0;
        int col = 0;

        ArrayList <GameState> adjacents = new ArrayList<GameState>();

        for(int i = 0; i<state.length; i++){
            for(int j = 0; j<state[i].length; j++){
                if(state[i][j] == 0){
                    row = i;
                    col = j;

                    if(row==0)
                        up = false;
                    if(row==2)
                        down = false;
                    if(col==0)
                        left = false;
                    if(col==2)
                        right = false;

                    break;
                }
            }
        }
        
        if(up){
            adjacents.add(swapUp(this, row, col));
        }
        if(down){
            adjacents.add(swapDown(this, row, col));
        }
        if(right){
            adjacents.add(swapRight(this, row, col));
        }
        if(left){
            adjacents.add(swapLeft(this, row, col));
        }

        return adjacents;

    }

    @Override
    public boolean equals(Object o) {
        //test that 2 GameStates are equal
        GameState newGame = (GameState) o;
        boolean isEquals = true;
        for(int i = 0; i < newGame.state.length; i++){
            for(int j = 0; j < newGame.state[i].length; j++){
                int curr = this.state[i][j];
                int newCurr = newGame.state[i][j];

                if(curr != newCurr)
                    isEquals = false;
            }
        }
        return isEquals;
    }

    @Override
    public String toString() {
        // print out the int[][] array in a 3x3 block e.g.
        //0 1 2
        //3 4 5
        //6 7 8
        String grid = "";
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                grid+=(state[i][j]+" ");
            }
            grid+="\n";
        }
        return grid;
    }
}

