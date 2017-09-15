package com.mustafa.data;

import java.util.List;

public interface ItemDAO {
	List<Items> getItemByType(ItemType type);
}
