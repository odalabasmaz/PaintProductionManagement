package tr.com.ppm.desktop.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;

import java.util.Arrays;
import java.util.List;

/**
 * @author ykarabalkan
 */
@RestController
public class PaintSubTypeController {
	private final PaintSubTypeService service;
	private final PaintTypeService paintTypeService;

	@Autowired
	public PaintSubTypeController(PaintSubTypeService service, PaintTypeService paintTypeService) {
		this.service = service;
		this.paintTypeService = paintTypeService;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			path = "/rest/paintSubTypes")
	@ResponseStatus(HttpStatus.OK)
	public List<PaintSubType> getPaintSubTypes(@RequestParam(value = "name", defaultValue = "") String name) {
		return StringUtils.isBlank(name) ? service.list() : service.list();
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/rest/paintSubTypes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PaintSubType getPaintSubType(@PathVariable(value = "id") long id) {
		return null;
//        return service.findById(id);
	}

	@RequestMapping(
			method = RequestMethod.POST,
			path = "/rest/paintSubTypes")
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	String addPaintSubType(@RequestParam(value = "name") String name, @RequestParam(value = "paintTypeId") Long paintTypeId) {

        /*PaintType paintType = paintTypeService.findById(paintTypeId);
        PaintSubType paintSubType = new PaintSubType(name, paintType);
        service.savePaintSubType(paintSubType);*/
		return "{\"result\": \"PaintSubType saved!\"}";
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			path = "/rest/paintSubTypes")
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	String updatePaintSubType(@RequestBody PaintSubType paintSubType) {
	    /*service.updatePaintSubType(paintSubType);*/
		return "{\"result\": \"PaintSubType updated!\"}";
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			path = "/rest/paintSubTypes")
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	String deletePaintSubType(@RequestParam(value = "id") long id) {
	    /*service.deletePaintSubTypeById(id);*/
		return "{\"result\": \"PaintSubType deleted!\"}";
	}

	@RequestMapping(
			method = RequestMethod.OPTIONS,
			path = "/rest/paintSubTypes")
	@ResponseStatus(value = HttpStatus.OK)
	public
	@ResponseBody
	List<RequestMethod> options() {
		return Arrays.asList(RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS);
	}
}