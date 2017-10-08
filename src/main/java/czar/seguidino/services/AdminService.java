package czar.seguidino.services;

import java.util.List;

import czar.seguidino.dtos.MenuItem;

public interface AdminService {
    List<MenuItem> buildAdminMenuItems(String username);
}
