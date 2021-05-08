package real.prop.vertical.Repository.Tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Tenant.TenantExpense;

@Repository
public interface TenantExpenseRepository extends JpaRepository<TenantExpense, String> {
}
