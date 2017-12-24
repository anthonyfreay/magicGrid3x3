import java.util.*;

public class Play {
    public static List<GameState> explored;  //explored stores our marked vertices
    public static Deque<GameState> frontier; //frontier is our queue for BFS
    public static void main(String[] args) {
        int[][] tArr = {{1,5,2},{3,4,0},{6,8,7}}; //start state, i.e. tree root
        GameState start = new GameState(tArr,null); // state with tArr state, null parent
        explored = new ArrayList<GameState>();
        frontier = new ArrayDeque<GameState>();

        frontier.add(start);
        boolean isFound = false;

        while(!frontier.isEmpty() && !isFound){
            GameState now = frontier.remove();
            if(now.isEnd() && now != null){
                isFound = true;
                explored.add(now);
                foundAnswer(now);
                break;
            }
            ArrayList<GameState> adj = now.getAdjacent();
            for(GameState a : adj){
                if(!explored.contains(a) && !frontier.contains(a)){
                    frontier.addLast(a);
                }
            }
            explored.add(now);
        }

    }
    public static void foundAnswer(GameState now){
        // helper function to print the answer found
        while(now != null){
            System.out.println(now.toString() + "\n");
            now = now.parent;
        }
    }
}
