package itemhistory.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import item.model.Item;

public class ItemHistory {
    private int id;
    private int itemId;
    private int quantity;
    private Type type;
    private Timestamp createdAt;

    public ItemHistory(int itemId, int quantity, Type type) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.type = type;
    }

    public ItemHistory(int id, int itemId, int quantity, Type type, Timestamp createdAt) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.type = type;
        this.createdAt = createdAt;
    }

    public static ItemHistory createFrom(Item item, int quantity, Type type) {
        return new ItemHistory(item.getId(), quantity, type);
    }

    public static ItemHistory createInbound(Item item, int quantity, Type type) {
        return new ItemHistory(item.getId(), quantity, Type.IN);
    }

    public static ItemHistory createOutbound(Item item, int quantity, Type type) {
        return new ItemHistory(item.getId(), quantity, Type.OUT);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Type getType() {
        return type;
    }

    public static ItemHistory createFrom(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        int itemId = resultSet.getInt("item_id");
        Type type = Type.valueOf(resultSet.getString("type"));
        int quantity = resultSet.getInt("quantity");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new ItemHistory(id, itemId, quantity, type, createdAt);
    }

}
