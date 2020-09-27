package com.mastertheboss.graphql.model;

import java.io.Serializable;


	public class Item implements Serializable {

		private static final long serialVersionUID = 1L;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}


		private Integer id;


		private String type;


		private String model;

		private int price;


		@Override
		public String toString() {
			return "Item{" +
					"id=" + id +
					", type='" + type + '\'' +
					", model='" + model + '\'' +
					", price=" + price +
					'}';
		}
	}