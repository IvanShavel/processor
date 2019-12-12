package com.stock.processor.repository;

import com.stock.processor.model.Company;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * CompanyRepository - provides basic methods for work with google cloud datastore
 *
 * @author Ivan Shavel
 */
@Async
public interface CompanyRepository extends DatastoreRepository<Company, String> {
}
