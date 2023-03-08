import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDb {

    private DbConnection dbConnection = new DbConnection();

    public List<String> createSetOfWriters(){
        List<String> expectedWriter = new ArrayList<>();
        expectedWriter.add("Taras Shevchenko");
        expectedWriter.add("Ivan Franko");
        expectedWriter.add("Lesya Ukrainka");
        return expectedWriter;
    }

    public List<String> createSetOfBooks(){
        List<String> expectedBooks = new ArrayList<>();
        expectedBooks.add("Poetry");
        expectedBooks.add("Zakhar Berkut");
        expectedBooks.add("Cassandra");
        return expectedBooks;
    }

    @Test
    public void testSelectQuery() throws SQLException{
        Connection conn = dbConnection.dbConnect();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT writerss.name FROM writerss WHERE writerss.id=1");
        while (rs.next()){
            Assert.assertEquals("Wrong name", createSetOfWriters().get(0), rs.getString("name"));
            System.out.println(createSetOfWriters().get(0) + "=" + rs.getString("name"));
        }
        dbConnection.dbEndConnect();
    }

    @Test
    public void testJoinQuery() throws SQLException{
        Connection conn = dbConnection.dbConnect();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT writerss.name, book FROM writerss JOIN writer ON writerss.id = writer.id WHERE writer.id = 3;");
        while (rs.next()){
            Assert.assertEquals("Wrong book", createSetOfBooks().get(2), rs.getString("book"));
            System.out.println(createSetOfBooks().get(2) + "=" + rs.getString("book"));
        }
        dbConnection.dbEndConnect();
    }
}
