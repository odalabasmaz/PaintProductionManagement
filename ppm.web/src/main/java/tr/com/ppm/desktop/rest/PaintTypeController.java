package tr.com.ppm.desktop.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintTypeService;

import java.util.Arrays;
import java.util.List;

/**
 * @author ykarabalkan
 */
@RestController
public class PaintTypeController {

	private final PaintTypeService service;

	@Autowired
	public PaintTypeController(PaintTypeService service) {
		this.service = service;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			path = "/rest/paintTypes")
	@ResponseStatus(HttpStatus.OK)
	public List<PaintType> getPaintTypes(@RequestParam(value = "name", defaultValue = "") String name) {
		return StringUtils.isBlank(name) ? service.findAllPaintTypes() : service.findByName(name);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/rest/paintTypes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PaintType getPaintType(@PathVariable(value = "id") long id) {
		return service.findById(id);
	}

	@RequestMapping(
			method = RequestMethod.POST,
			path = "/rest/paintTypes")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String addPaintType(@RequestBody PaintType paintType) {
		service.savePaintType(paintType);
		return "{\"result\": \"PaintType saved!\"}";
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			path = "/rest/paintTypes")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String updatePaintType(@RequestBody PaintType paintType) {
		service.updatePaintType(paintType);
		return "{\"result\": \"PaintType updated!\"}";
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			path = "/rest/paintTypes")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String deletePaintType(@RequestParam(value = "id") long id) {
		service.deletePaintTypeById(id);
		return "{\"result\": \"PaintType deleted!\"}";
	}

	@RequestMapping(
			method = RequestMethod.OPTIONS,
			path = "/rest/paintTypes")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<RequestMethod> options() {
		return Arrays.asList(RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS);
	}

}