public class Board {
    Location[][] squares = new Location[24][24];
    public Board() {
        /**
         * Creating the board objects in an array
         */
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                if ((1 < i && i < 7) && (1 < j && j < 7))
                    squares[i][j] = new Estate(new Position(i, j), "haunted house"); // haunted house
                else if ((16 < i && i < 22) && (1 < j && j < 7))
                    squares[i][j] = new Estate(new Position(i, j), "calamity castle"); // calamity castle
                else if ((1 < i && i < 7) && (16 < j && j < 22))
                    squares[i][j] = new Estate(new Position(i, j), "manic manor"); // manic manor
                else if ((16 < i && i < 22) && (16 < j && j < 22))
                    squares[i][j] = new Estate(new Position(i, j), "peril palace"); // peril palace
                else if ((9 < i && i < 14) && (8 < j && j < 15))
                    squares[i][j] = new Estate(new Position(i, j), "villa ciella"); // villa ciella
                /**
                 * Create Pillars aka. "Walls"
                 */
                else if ((4 < i && i < 7) && (10 < j && j < 13))
                    squares[i][j] = new Wall(new Position(i, j));
                else if ((16 < i && i < 19) && (10 < j && j < 13))
                    squares[i][j] = new Wall(new Position(i, j));
                else if ((10 < i && i < 13) && (16 < j && j < 19))
                    squares[i][j] = new Wall(new Position(i, j));
                else if ((10 < i && i < 13) && (4 < j && j < 7))
                    squares[i][j] = new Wall(new Position(i, j));
                /**
                 * Deaut Square aka "free"
                 */
                else squares[i][j] = new Free(new Position(i, j));
            }
        }
        /**
         * Assigning the board objects they're neighbours
         */
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                if (i - 1 > -1) squares[i][j].setNorth(squares[i - 1][j]);
                if (i + 1 < 24) squares[i][j].setSouth(squares[i + 1][j]);
                if (j - 1 > -1) squares[i][j].setWest(squares[i][j - 1]);
                if (j + 1 < 24) squares[i][j].setEast(squares[i][j + 1]);
            }
        }
        /**
         * Re-Assigning locations in estate's neighbours as squares outside entrances
         * Making all the estate squares have only the squares outside they're entrance as neighbours so that moving while inside an estate pushes you out one of the entrances
         */
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                /**
                 * Haunted house squares
                 * setting north and west neighbours as the north exit
                 *  & setting east and south neighbours as the east exit
                 */
                if ((1 < i && i < 7) && (1 < j && j < 7)) {
                    squares[i][j].setNorth(squares[3][7]);
                    squares[i][j].setWest(squares[7][5]);
                    squares[i][j].setEast(squares[3][7]);
                    squares[i][j].setSouth(squares[7][5]);
                }
                /**
                 * Calamity Castle Squares
                 * Setting south and west neighbours as the south exit
                 * and north and east as the east exit
                 */
                else if ((16 < i && i < 22) && (1 < j && j < 7)){
                    squares[i][j].setNorth(squares[16][3]);
                    squares[i][j].setWest(squares[16][3]);
                    squares[i][j].setEast(squares[18][7]);
                    squares[i][j].setSouth(squares[18][7]);
                }
                /**
                 * Manic Manor Squares
                 * setting south and east neighbours as south exit
                 * and west and north as west exit
                 */
                else if ((1 < i && i < 7) && (16 < j && j < 22)){
                    squares[i][j].setNorth(squares[5][16]);
                    squares[i][j].setWest(squares[5][16]);
                    squares[i][j].setEast(squares[7][20]);
                    squares[i][j].setSouth(squares[7][20]);
                }
                /**
                 * peril Palace
                 * setting south and west neighbours as west exit
                 * and north and east neighbours as north exit
                 */
                else if ((16 < i && i < 22) && (16 < j && j < 22)){
                    squares[i][j].setNorth(squares[16][18]);
                    squares[i][j].setWest(squares[20][16]);
                    squares[i][j].setEast(squares[16][18]);
                    squares[i][j].setSouth(squares[20][16]);
                }
                /**
                 * villa Celia
                 * Setting north neighbour as north exit, south as south etc...
                 */
                else if ((9 < i && i < 14) && (8 < j && j < 15)){
                    squares[i][j].setNorth(squares[9][12]);
                    squares[i][j].setWest(squares[8][12]);
                    squares[i][j].setEast(squares[11][15]);
                    squares[i][j].setSouth(squares[14][11]);
                }



            }
        }
    }


    /**
     * Draws the game board and pieces
     */
    public void drawBoard() {
        String[] board = new String[24];
        String current = "";
        for (int i = 0; i < 24; i++) {
            current = "";
            for (int j = 0; j < 24; j++) {
                if (squares[i][j] instanceof Estate) current += "[]";
                else if (squares[i][j] instanceof Wall) current += "WW";
                else current += "--";
            }
            board[i] = current;
        }
        for (int i = 0; i < 24; i++) {
            System.out.println(board[i]);
        }


    }
}

