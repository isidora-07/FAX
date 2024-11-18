package Models;

public class File {
    private String name;
    private String meme;
    private boolean folder;
    private String path;

    public File() {
        super();
    }

    public File(String name, String meme, boolean folder) {
        super();
        this.name = name;
        this.meme = meme;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
