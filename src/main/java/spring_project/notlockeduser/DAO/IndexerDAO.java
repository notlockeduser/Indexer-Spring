package spring_project.notlockeduser.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import spring_project.notlockeduser.models.Indexer;

import java.util.List;

@Component
@PropertySource("classpath:taxi.properties")
public class IndexerDAO {
    Indexer indexer;
    static int n = 5;

    static String rootPath = "C:\\Users\\Bogdan\\Documents\\GitHub\\Parallel-processing-Course-work";
    static String inputPath = "C:\\input\\";
    static String stopWordsPath = rootPath + "\\assets\\StopWords.txt";

    @Autowired
    public IndexerDAO() {
        this.indexer = new Indexer(n, inputPath, stopWordsPath);
    }

    public List<String> getResponse(String request) {
        return indexer.searchIndex(request);
    }

    public List<String> getFile(String path) {
        return indexer.getFile(path);
    }
}
