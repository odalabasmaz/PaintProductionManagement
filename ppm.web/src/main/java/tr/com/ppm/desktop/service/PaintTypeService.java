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
public class PaintTypeService {

	@Autowired
	private PaintTypeRepository paintTypeRepository;

	public PaintType findById(Long id) {
		return paintTypeRepository.findOne(id);
	}

	public List<PaintType> findByName(String name) {
		return paintTypeRepository.findByNameContainingIgnoreCase(name);
	}

	public void savePaintType(PaintType paintType) {
		paintTypeRepository.save(paintType);
	}

	public void updatePaintType(PaintType paintType) {
		savePaintType(paintType);
	}

	public void deletePaintType(PaintType paintType) {
		paintTypeRepository.delete(paintType);
	}

	public void deletePaintTypeById(Long id) {
		paintTypeRepository.delete(id);
	}

	public void deleteAllPaintTypes() {
		paintTypeRepository.deleteAll();
	}

	public List<PaintType> findAllPaintTypes() {
		return paintTypeRepository.findAll();
	}

}
