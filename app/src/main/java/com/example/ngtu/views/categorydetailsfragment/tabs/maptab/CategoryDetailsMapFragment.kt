package com.example.ngtu.views.categorydetailsfragment.tabs.maptab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentCategoryDetailsMapBinding
import com.example.ngtu.models.Category
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition


class CategoryDetailsMapFragment : BaseFragment() {

    class Screen(
        val categoryType: Category
    ) : BaseScreen

    override val viewModel by screenViewModel<CategoryDetailsMapViewModel>()

    private lateinit var binding: FragmentCategoryDetailsMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryDetailsMapBinding.inflate(inflater, container, false)

        binding.mapview.map.move(
            CameraPosition(Point(54.987719, 82.906436), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )

        viewModel.shopsList.observe(viewLifecycleOwner) { shopList ->
            shopList.forEach { shop ->
                shop.geo?.let {
                    val point = Point(it.latitude, it.longitude)
                    val placemark = binding.mapview.map.mapObjects.addPlacemark(point)
//                    placemark.setIcon(ImageProvider.fromResource(requireContext(), R.drawable.map_location))
                    placemark.setText(shop.name!!)

                }
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        binding.mapview.onStop()
    }
}