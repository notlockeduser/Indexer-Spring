package spring_project.notlockeduser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring_project.notlockeduser.DAO.IndexerDAO;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    IndexerDAO indexerDAO;

    @Autowired
    public SearchController(IndexerDAO indexerDAO) {
        this.indexerDAO = indexerDAO;
    }

    @GetMapping("")
    public String getSearchPage(@RequestParam(value = "request", required=false) String request,
                                Model model) {
        if (request==null)
            return "search/search";
        List<String> array = indexerDAO.getResponse(request);
        for (int i = 0; i < array.size(); i++) {
            array.set(i, new String(Base64.getDecoder().decode(array.get(i))));
        }
        model.addAttribute("array",array);
        model.addAttribute("responseCode",indexerDAO.getResponse(request));
        return "search/search";
    }

    @GetMapping("/{path}")
    public String getOnId(@PathVariable("path") String path, Model model) {
        String realPath = new String(Base64.getDecoder().decode(path));
        model.addAttribute("path", realPath);
        model.addAttribute("textFile", indexerDAO.getFile(realPath));
        return "search/file";
    }


}
