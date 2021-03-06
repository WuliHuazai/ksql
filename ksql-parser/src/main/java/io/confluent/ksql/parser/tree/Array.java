/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.parser.tree;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

public class Array extends Type {

  private Type itemType;

  public Array(final Type itemType) {
    this(Optional.empty(), itemType);
  }

  public Array(final NodeLocation location, final Type itemType) {
    this(Optional.of(location), itemType);
  }

  private Array(final Optional<NodeLocation> location, final Type itemType) {
    super(location, SqlType.ARRAY);
    requireNonNull(itemType, "itemType is null");
    this.itemType = itemType;
  }

  @Override
  public <R, C> R accept(final AstVisitor<R, C> visitor, final C context) {
    return visitor.visitArray(this, context);
  }

  public Type getItemType() {
    return itemType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemType);
  }

  @Override
  public boolean equals(final Object obj) {
    return
        obj instanceof Array
        && Objects.equals(itemType, ((Array)obj).itemType);
  }
}
