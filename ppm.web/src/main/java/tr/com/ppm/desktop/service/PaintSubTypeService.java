package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.repository.PaintSubTypeRepository;

import java.util.List;

/**
 * @author uadak
 */
@Service
public class PaintSubTypeService {

	@Autowired
	private PaintSubTypeRepository paintSubTypeRepository;

	public List<PaintSubType> findByName(String name) {
		return paintSubTypeRepository.findByNameContainingIgnoreCase(name);
	}

	public PaintSubType findById(Long id) {
		return paintSubTypeRepository.findOne(id);
	}

	public void savePaintSubType(PaintSubType paintSubType) {
		paintSubTypeRepository.save(paintSubType);
	}

	public void updatePaintSubType(PaintSubType paintSubType) {
		savePaintSubType(paintSubType);
	}

	public void deletePaintSubType(PaintSubType paintSubType) {
		paintSubTypeRepository.delete(paintSubType);
	}

	public void deletePaintSubTypeById(Long id) {
		paintSubTypeRepository.delete(id);
	}

	public void deleteAllPaintSubTypes() {
		paintSubTypeRepository.deleteAll();
	}

	public List<PaintSubType> findAllPaintSubTypes() {
		return paintSubTypeRepository.findAll();
	}
}
