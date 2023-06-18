package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.entity.AuthorizationType
import com.example.entity.CardEntity
import com.example.entity.CategoryTransactionType
import com.example.entity.TransactionEntity
import com.example.entity.UserEntity
import com.example.presentation.core.EmptySideEffect
import com.example.presentation.core_compose.CircularProgressBar
import com.example.presentation.theme.Black300
import com.example.presentation.theme.White100
import com.example.presentation.theme.White400
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransactionCategory
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransaction
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BaseBankTransaction
import com.example.presentation.ui.bottombar.tabs.home.model.HomeViewModel
import kotlinx.coroutines.launch


@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    val cardEntity = CardEntity()
    val transactions: List<BaseBankTransaction> = listOf(
        BankTransactionCategory(CategoryTransactionType.Recent),
        BankTransaction(TransactionEntity("1")),
        BankTransaction(TransactionEntity("2")),
        BankTransaction(TransactionEntity("3")),
        BankTransaction(TransactionEntity("4")),
        BankTransaction(TransactionEntity("5")),
        BankTransaction(TransactionEntity("6")),
    )
    val cards = mutableListOf(BankCard(cardEntity))
    val user = UserEntity("1", "", AuthorizationType.Null, name = "Dmitry")
    HomeScreen(cards = { cards }, transactions = { transactions }, user = { user })
}

@Composable
fun HomeRoute(
    onDetailCardClicked: () -> Unit = {},
    onAddNewCardClicked: () -> Unit = {},
    openProfileClicked: () -> Unit = {},
    sendCardClicked: () -> Unit = {},
    requestCardClicked: () -> Unit = {},
    payCardClicked: () -> Unit = {},
    moreOptionsClicked: () -> Unit = {},
    onTransactionClicked: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val events = viewModel.sideEffectFlow.collectAsStateWithLifecycle(EmptySideEffect)
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val userState = state.value.user.collectAsStateWithLifecycle()
    val cardsState = state.value.cards.collectAsStateWithLifecycle()
    val transactionState = state.value.transactions.collectAsStateWithLifecycle()
    val selectedCardPosition = state.value.selectedCardPosition.collectAsStateWithLifecycle()
    val isUserLoading = state.value.isUserLoading.collectAsStateWithLifecycle()
    val isBankCardsLoading = state.value.isBankCardsLoading.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel, block = {
        snapshotFlow { events.value }
            .collect {
                when (it) {

                }
            }
    })

    HomeScreen(
        { userState.value },
        { cardsState.value },
        { transactionState.value },
        { selectedCardPosition.value },
        { isBankCardsLoading.value },
        onDetailCardClicked,
        onAddNewCardClicked,
        openProfileClicked,
        sendCardClicked,
        requestCardClicked,
        payCardClicked,
        moreOptionsClicked,
        onTransactionClicked,
        viewModel::loadMoreTransactions,
        viewModel::updateSelectedCard,
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
internal fun HomeScreen(
    user: () -> UserEntity,
    cards: () -> List<BankCard>,
    transactions: () -> List<BaseBankTransaction>,
    selectedCardPosition: () -> Int = { 0 },
    isBackCardLoading: () -> Boolean = { false },
    onDetailCardClicked: () -> Unit = {},
    onAddNewCardClicked: () -> Unit = {},
    openProfileClicked: () -> Unit = {},
    sendCardClicked: () -> Unit = {},
    requestCardClicked: () -> Unit = {},
    payCardClicked: () -> Unit = {},
    moreOptionsClicked: () -> Unit = {},
    onTransactionClicked: () -> Unit = {},
    onLoadMore: (String) -> Unit = {},
    updateSelectedCardPosition: (Int) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .background(White400)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressBar(
            Modifier.align(Alignment.Center),
            isLoading = isBackCardLoading,
            colorProgress = White100,
            colorIndicator = Black300
        )

        val pagerState: PagerState = rememberPagerState(0)

        val listState: LazyListState = rememberLazyListState()

        val shouldLoadMore by remember { derivedStateOf { listState.isNeedLoadMore() } }

        LaunchedEffect(pagerState.settledPage) {
            updateSelectedCardPosition.invoke(pagerState.settledPage)
        }

        val cardsModifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(top = 20.dp)

        val cardOptionsModifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 20.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)

        val transactionModifier = Modifier
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)

        val bankCards = cards().ifEmpty { return }
        val bankCard = bankCards[selectedCardPosition.invoke()]

        LazyColumn(modifier = Modifier.fillMaxSize(), listState) {
            item(key = 0) {
                CardsScreen(
                    cardsModifier,
                    pagerState,
                    user,
                    cards,
                    onDetailCardClicked,
                    onAddNewCardClicked,
                    openProfileClicked
                )
            }
            stickyHeader {
                CardOptions(
                    cardOptionsModifier,
                    sendCardClicked,
                    requestCardClicked,
                    payCardClicked,
                    moreOptionsClicked
                )
            }

            items(transactions.invoke(), key = { it.id }) {
                Row(Modifier.animateItemPlacement()) {
                    when (it) {
                        is BankTransactionCategory ->
                            CategoryTransactionItem(transactionModifier, it.category)

                        is BankTransaction -> TransactionItem(
                            transactionModifier,
                            it.entity,
                            bankCard.card.paymentType,
                            onTransactionClicked
                        )
                    }
                    if (shouldLoadMore) onLoadMore.invoke(bankCard.card.id)
                }
            }

        }
    }
}

fun LazyListState.isNeedLoadMore(): Boolean =
    (layoutInfo.visibleItemsInfo.getOrNull(layoutInfo.visibleItemsInfo.lastIndex)?.index ?: -1) >=
            layoutInfo.totalItemsCount * 1.5 / 2
