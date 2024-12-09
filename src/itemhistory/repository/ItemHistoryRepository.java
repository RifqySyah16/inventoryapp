package itemhistory.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import common.CRUDRepository;
import itemhistory.model.ItemHistory;

public class ItemHistoryRepository implements CRUDRepository<ItemHistory> {
    private Connection connection;

    public ItemHistoryRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ItemHistory> getAll() {
        String sql = "SELECT * FROM item_history";

        List<ItemHistory> itemHistories = new ArrayList<>();

        try {
            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ItemHistory itemHistory = ItemHistory.createFrom(resultSet);

                itemHistories.add(itemHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemHistories;
    }

    @Override
    public int add(ItemHistory t) {
        String sql = "INSERT INTO item_history (item_id, quantity, type) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, t.getItemId());
            preparedStatement.setInt(2, t.getQuantity());
            preparedStatement.setString(3, t.getType().getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int update(ItemHistory t) {
        String sql = "UPDATE INTO item_history SET (quantitiy = ?, type = ?) WHERE item_id = ? ";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, t.getQuantity());
            preparedStatement.setString(2, t.getType().getName());
            preparedStatement.setInt(3, t.getItemId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int remove(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public Optional<ItemHistory> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
