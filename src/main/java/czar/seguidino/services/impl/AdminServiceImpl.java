package czar.seguidino.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import czar.seguidino.dtos.MenuItem;
import czar.seguidino.services.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Override
    public List<MenuItem> buildAdminMenuItems(String username) {
        List<MenuItem> menu = new ArrayList<>();
        MenuItem mi = new MenuItem("/admin/projects/list", "Projects", false, false);
        menu.add(mi);
        return menu;
    }

}
