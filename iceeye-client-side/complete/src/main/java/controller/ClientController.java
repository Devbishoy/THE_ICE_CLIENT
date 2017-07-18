package controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Param;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AssignmentInfo;
import service.ClientConnectorService;

@Controller
public class ClientController {

	final static ClientConnectorService clientConnectorInstance = new ClientConnectorService();

	@RequestMapping("/assignment")
	public String assignment(Model model) {
		try {
			ObjectInputStream fromServer = clientConnectorInstance
					.getServerConnection(clientConnectorInstance.ASSIGNMENT, "");
			List<AssignmentInfo> assignmentList = clientConnectorInstance.getAssignment(fromServer);
			model.addAttribute("assignmentList", assignmentList);

		} catch (ClassNotFoundException | IOException e) {
			// TODO log Exception
			e.printStackTrace();
		}
		return "assignment";
	}

	@RequestMapping("/ingest")
	public String ingest(@RequestParam(value = "text", required = false, defaultValue = "World") String text,
			Model model) {
		model.addAttribute("name", "name");
		return "ingest";
	}

	@RequestMapping(value = "/ingest/download", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public FileSystemResource downloadFile(@RequestParam(value = "text") String text) {
		ObjectInputStream fromServer;
		try {
			fromServer = clientConnectorInstance.getServerConnection(clientConnectorInstance.INGEST, text);
			String url = clientConnectorInstance.getIngest(fromServer);
			return new FileSystemResource(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
