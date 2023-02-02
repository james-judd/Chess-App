public class Input{
    
    public static int[] takeStart(Board chessboard){
        int[] input = Input.takeInput(true);
        while (chessboard.board[input[0]][input[1]] == null){
                System.out.println("There's no piece there");
                input = Input.takeInput(true);
            }
        return (input);
    }

    public static int[] takeInput(boolean isStart){
        int[] input = parseInput(isStart);
        while (input[0] < 0 || input[0] > 7 || input[1] < 0 || input[1] > 7){
            System.out.println("Out of bounds");
            input = parseInput(isStart);
        }
        return (input);
    }

    public static int[] parseInput(boolean isStart){
        int[] input = new int[2];
        if (isStart == true){
            System.out.print("Starting square: ");
        }
        else{
            System.out.print("Target square: ");
        }
        String inString = System.console().readLine();
        switch (Character.toUpperCase(inString.charAt(0))){
            case 'A':
                input[1] = 0;
                break;
            case 'B':
                input[1] = 1;
                break;
            case 'C':
                input[1] = 2;
                break;
            case 'D':
                input[1] = 3;
                break;
            case 'E':
                input[1] = 4;
                break;
            case 'F':
                input[1] = 5;
                break;
            case 'G':
                input[1] = 6;
                break;
            case 'H':
                input[1] = 7;
                break;
            default:
                input[1] = 8;
        }                                                                             
        input[0] = Character.getNumericValue(inString.charAt(1)) - 1;
        return (input);
    }
}
