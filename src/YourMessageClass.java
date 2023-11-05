public class YourMessageClass {
    private String content;

    public YourMessageClass() {
        // Default constructor for Jackson
    }

    public YourMessageClass(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}