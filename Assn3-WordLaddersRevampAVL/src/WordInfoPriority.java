public class WordInfoPriority extends WordInfo implements Comparable<WordInfoPriority> {

    private int priority;
    private int estimatedWork;

    @Override
    public int compareTo(WordInfoPriority priorityValue) {
        if (priorityValue.getPriority() == this.priority) {
            return 0;
        } else if (priorityValue.getPriority() > this.priority) {
            return -1;
        } else {
            return 1;
        }
    }

    public WordInfoPriority(String word, int moves, int estimatedWork) {
        super(word, moves);
        this.estimatedWork = estimatedWork;
        priority = moves + estimatedWork;
    }

    public WordInfoPriority(String word, int moves, int estimatedWork, String history) {
        super(word, moves, history);
        this.estimatedWork = estimatedWork;
        priority = moves + estimatedWork;
    }

    public int getEstimatedWork() { return this.estimatedWork; }

    public int getPriority() { return this.priority; }

    public String getWord() { return this.word; }

    @Override
    public String toString() {
        return String.format("Word %s Moves %d : History[%s]    estimated work: %d",
                word, moves, history, estimatedWork);
    }
}




    class WordInfoPrev implements Comparable<WordInfoPrev> {

        private int lengthOfLadder;
        private String word;

        public WordInfoPrev(String word, int lengthOfLadder) {
            this.word = word;
            this.lengthOfLadder = lengthOfLadder;
        }

        @Override
        public int compareTo(WordInfoPrev wordInfoPrevObject) {
            return wordInfoPrevObject.word.compareTo(this.word);
        }

        public String getWord() { return this.word; }

        public int getLengthOfLadder() { return this.lengthOfLadder; }

        public void setLengthOfLadder(int lengthOfLadder) { this.lengthOfLadder = lengthOfLadder; }
    }
