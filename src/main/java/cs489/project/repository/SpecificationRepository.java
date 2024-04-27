package cs489.project.repository;

import cs489.project.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
}
