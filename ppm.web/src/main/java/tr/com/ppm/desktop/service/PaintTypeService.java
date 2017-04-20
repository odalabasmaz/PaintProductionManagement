package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.repository.PaintTypeRepository;

import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class PaintTypeService extends BaseNewService<PaintType, Long> {

	private PaintTypeRepository repository;

	@Autowired
	public PaintTypeService(PaintTypeRepository repository) {
		super(repository);
	}

	public List<PaintType> findByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

}
