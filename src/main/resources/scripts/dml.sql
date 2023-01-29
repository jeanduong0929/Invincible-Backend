-- ###################
-- ##     ROLE      ##
-- ###################
--- INSERT
INSERT INTO
  inv.in_role (id, role)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f0', 'ADMIN');

INSERT INTO
  inv.in_role (id, role)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f1', 'DEFAULT');

-- ###################
-- ##     USER      ##
-- ###################
--- INSERT
--- UPDATE
UPDATE inv.in_user
SET
  role_id = 'e0438fd6-0f95-419b-8e04-3207d3e941f0'
WHERE
  username = 'bduong0929';

-- #######################
-- ##     CATEGORY      ##
-- #######################
--- INSERT
INSERT INTO
  inv.in_category (id, category)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f0', 'Tops');

-- ######################
-- ##     PRODUCT      ##
-- ######################
--- INSERT
INSERT INTO
  inv.in_product (id, price, product, category_id)
VALUES
  (
    'e0438fd6-0f95-419b-8e04-3207d3e941f0',
    59.99,
    'Invincible Black Boxie Tee',
    'e0438fd6-0f95-419b-8e04-3207d3e941f0'
  );

;

-- ###################
-- ##     SIZE      ##
-- ###################
INSERT INTO
  inv.in_size (id, size)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f0', 'S');

INSERT INTO
  inv.in_size (id, size)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f1', 'M');

INSERT INTO
  inv.in_size (id, size)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f2', 'L');

-- #######################
-- ##     QUANTITY      ##
-- #######################
--- INSERT
INSERT INTO
  inv.in_quantity (id, quantity)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f0', 10);

INSERT INTO
  inv.in_quantity (id, quantity)
VALUES
  ('e0438fd6-0f95-419b-8e04-3207d3e941f1', 10);

-- ############################
-- ##     PRODUCTS_SIZE      ##
-- ############################
--- INSERT
INSERT INTO
  inv.in_products_sizes (product_id, size_id)
VALUES
  (
    'e0438fd6-0f95-419b-8e04-3207d3e941f0',
    'e0438fd6-0f95-419b-8e04-3207d3e941f1'
  );

-- ###############################
-- ##     SIZES_QUANTITIES      ##
-- ###############################
--- INSERT
INSERT INTO
  inv.in_sizes_quantities (size_id, quantity_id)
VALUES
  (
    'e0438fd6-0f95-419b-8e04-3207d3e941f1',
    'e0438fd6-0f95-419b-8e04-3207d3e941f1'
  );
