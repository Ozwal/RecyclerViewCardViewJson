package tinoco.castro.recyclerviewcardviewjson;

public class ListaItem {
    private String head;
    //private String desc;
    private String imageUri;

    public ListaItem(String head, String imageUri) {
        this.head = head;
        //this.desc = desc;
        this.imageUri = imageUri;
    }

    public String getHead() {
        return head;
    }

    //public String getDesc() {
    //    return desc;
    //}

    public String getImageUri() {
        return imageUri;
    }
}
