package item.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import common.CRUDRepository;
import item.model.Item;

public class ItemRepository implements CRUDRepository<Item> {
    private Connection connection;

    public ItemRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();

        try {
            String sql = "SELECT * FROM item";

            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                Item item = new Item(id, name, quantity);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public int add(Item item) {
        String sql = "NSERT INTO item (name, quantity) VALUES (?, ?)";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setInt(2, item.getQuantity());

            int insertedRows = statement.executeUpdate();

            if (insertedRows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int remove(int id) {
        String sql = "DELETE FROM item WHERE id = ?";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);

            int removedRows = statement.executeUpdate();

            if (removedRows > 0) {
                return removedRows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int update(Item t) {
        String sql = "UPDATE item SET quantity = ? where id = ?";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, t.getQuantity());
            statement.setInt(2, t.getId());

            int updatedRows = statement.executeUpdate();

            if (updatedRows > 0) {
                return updatedRows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Optional<Item> findById(int id) {
        String sql = "SELECT * FROM item WHERE id = ?";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");

                return Optional.of(new Item(name, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Item> findByName(String name) {
        String sql = "SELECT * FROM item WHERE name = ? LIMIT 1";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String itemName = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");

                return Optional.of(new Item(itemName, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
