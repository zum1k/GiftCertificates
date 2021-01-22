package com.epam.esm.repository.specification;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.NativeSpecification;

public class MostWidelyUsedTagByUserOrders implements NativeSpecification<Tag> {
  private static final String QUERY =
      "SELECT tags.tag_id,tags.name,tags.create_date,tags.last_update_date" +
          " FROM tags WHERE tags.tag_id = (SELECT gifts_tags.tag_id" +
          " FROM gifts_tags WHERE gifts_tags.gift_id IN" +
          " (SELECT gift_id FROM orders WHERE user_id=" +
          "(SELECT user_id FROM orders WHERE price =" +
          "(SELECT MAX(price) FROM orders)LIMIT 1))" +
          " GROUP BY tag_id ORDER BY COUNT(tag_id) DESC LIMIT 1);";

  @Override
  public String getNativeQuery() {
    return QUERY;
  }
}
