public class Node {
    private final String key;
    private String value;

    private Node prev;
    private Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    private final Map<String, Node> map;
    private final int capacity;

    private Node head = null;
    private Node tail = null;

    public LRUCache(int capacity) {
        this.map = new HashMap<String, Node>();
        this.capacity = capacity;
    }

    public String get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);
        deleteFromList(node);
        setListHead(node);

        return node.value;
    }

    public String put(String key, String value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.setValue(value);

            deleteFromList(node);
            setListHead(node);
        } else {
            if (map.size() == capacity) {
                map.remove(tail.getKey());
                deleteFromList(tail);
            }

            Node node = new Node(key, value);

            map.put(key, value);
            setListHead(node)
        }
    }

    private void deleteFromList(Node node) {
        capacity -= 1;
        if (node==this.head) {
            setListHead(head.next);
        } else if (node==this.tail) {
            this.tail = this.tail.prev;
            this.tail.next = null;
        } else {
            Node temp = node;
            node.prev.next = temp.next;
            node.next.prev = temp.prev;
        }
    }

    private void setListHead(Node node) {
        node.next = this.head;
        this.head.prev = node;
        this.head = node;
    }
}